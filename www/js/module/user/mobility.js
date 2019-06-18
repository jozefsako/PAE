$(function () {

  let id_mobility_choice = undefined;

  /*
   * Get the id of the current mobility_choice
   * When mouseover
   */
  $("#table_mobilites").on("mouseover", "tr", function () {
    id_mobility_choice = $(this).attr('id');
  });

  /*
   * OnClick Info Button
   */
  $("#table_mobilites").on("click", "tr a", function (event) {
    event.preventDefault();
    getAllDataRelatedToMobility(id_mobility_choice);
    closeCheckBoxes(ViewUser.MOBILITY_INFO);
    hideView(ViewUser.MOBILITES);
    hideView(ViewUser.MOBILITY_SEARCH);
    displayView(ViewUser.MOBILITY_INFO);
    displayView(ViewUser.MOBILITY_DOC);
  });

  /*
   * OnClick Update Button
   */
  $("#btn_update_mobility").on("click", function (event) {
    event.preventDefault();
    selectNewDocuments();
  });

  /*
   * Update Tools
   */
  $("#btn_update_mobility_tools").on("click", function (event) {
    event.preventDefault();
    updateTools(selected_mobility);
  });

  /*
   * Accept Payement
   */
  $("#btn_accept_payement").on("click", function (event) {
    event.preventDefault();
    confirmPayement(selected_mobility);
  });

  /*
   * Display Cancelation
   */
  $("#btn_cancel_mobility").on("click", function (event) {
    event.preventDefault();

    /* Collect reasons */
    getCancelationReasons(selected_mobility);
    hideView(ViewUser.MOBILITY_DOC);
    displayView(ViewUser.MOBILITY_CANCELATION);
    $('#div_btn_update_mobility').css('display', 'none');
    $('#div_btn_cancel_mobility').css('display', 'none');
  });

  /*
   * Cancel Mobility
   */
  $("#btn_confirm_cancelation").on("click", function (event) {
    event.preventDefault();
    cancelMobility(selected_mobility);
  });

  /*
   * Display Docs
   */
  $("#btn_revert_cancelation").on("click", function (event) {
    event.preventDefault();
    $('#div_btn_update_mobility').css('display', 'block');
    $('#div_btn_cancel_mobility').css('display', 'block');

    hideView(ViewUser.MOBILITY_CANCELATION);
    displayView(ViewUser.MOBILITY_DOC);

    let country = countries.find(
        c => c.countryCode3 === selected_mobility_choice.country);
    if (country.takenCareOf === true) {
      $('#div_taken_care_of').css('display', 'block');
      $('#div_docs').css('display', 'none');
    }
  });

  /*
   * Search mobilites
   */
  $("#searchMobility_button").on("click", function () {
    searchMobility();
    displayView(ViewUser.MOBILITES);
  });
});

function displayUserCard(user) {
  if (user !== undefined) {
    $("#card_student ").html(
        user.firstName + " " +
        user.lastName + "</br>" +
        "<p style='color: #6e707e; font-size:10px;'>" + user.email
        + "</p>");
  }
}

function getMobilites() {
  $.ajax({
    type: 'POST',
    url: '/',
    data: {
      action: "getMobilities"
    },
    success: function (resp) {
      displayMobilities(resp);
    }
  });
}

function displayMobilities(resp) {
  let listUsers;
  if (isIterable(resp)) {
    listUsers = resp;
    let rows = "";
    for (let mobility of listUsers) {
      console.log(mobility.idMobility);
      rows += "<tr id='" + mobility.idMobility + "'>";

      if(current_user.typeUser === "S"){
        rows += createTableRow(current_user.firstName + " " + current_user.lastName);
      }else{
        let i_mobchoice = mobility_choices.find(mobilityChoice => mobilityChoice.idMobilityChoice === mobility.idMobility);
        let i_user = users.find(u => u.id === i_mobchoice.idUser);
        rows += createTableRow(i_user.firstName + " " + i_user.lastName);
      }

      //rows += createTableRow(mobility.idMobility);
      //rows += createTableRow(i_user.firstName + " " + i_user.lastName);
      rows += createTableRow(
          checkNull(
              getMobilityConfirmation(mobility.mobilityConfirmation)));
      rows += createTableRow(
          checkNull(getMobilityState(mobility.mobilityState, "FR")));
      rows += createTableRow(
          !mobility.mobilityTool ? getCheckBox() : getCheckBox(true));
      rows += createTableRow(
          !mobility.proEco ? getCheckBox() : getCheckBox(true));
      rows += createTableRow(
          mobility.paymentSent ? getCheckBox(true) : getCheckBox());
      rows += createTableRow(
          infoButton());
    }
    $("#tbody_mobilites").html(rows);
  } else {

  }
}

function getMobilityState(mobilityConfirmation, language) {
  if (language === "FR") {
    return EnumStateMobility.properties[mobilityConfirmation].FR;
  } else if (language == "EN") {
    return EnumStateMobility.properties[mobilityConfirmation].EN;
  }
}

function getMobilityConfirmation(mobilityConfirmation) {
  return EnumMobilityConfirmation.properties[mobilityConfirmation].name;
}

function getOrganisationType(organisation) {
  return EnumTypeOrganisation.properties[organisation].name;
}

function getMobilityProgram(program) {
  return EnumMobilityProgram.properties[program].name;
}

function displayCardMobilityChoice(mobilityChoice) {
  if (mobilityChoice !== undefined) {
    let country = countries.find(
        country => country.countryCode3 === mobilityChoice.country);
    let html_card = mobilityChoice.mobilityType + " - " +
        country.countryName + "</br>" +
        "<p style='color: #6e707e; font-size:10px;'>"
        + "Préférence : " + mobilityChoice.preferenceOrder + "<br/>"
        + "Prog : " + getMobilityProgram(mobilityChoice.program)
        + "</p>"
    $("#card_mobility_choice ").html(html_card);

    if (country.takenCareOf) {
      $('#div_taken_care_of').css('display', 'block');
      $('#div_docs').css('display', 'none');
    } else {
      $('#div_taken_care_of').css('display', 'none');
      $('#div_docs').css('display', 'block');
    }
  }
}

function getAllDataRelatedToMobility(idMobility) {
  $.ajax({
    type: 'POST',
    url: '/',
    data: {
      action: "getAllDataRelatedToMobility",
      idMobility: idMobility,
    },
    success: function (resp) {
      selected_partner = resp["org"]
      selected_user = resp["user"];
      selected_mobility_choice = resp["mobilityChoice"];
      selected_docs = resp["docs"];
      selected_mobility = resp["mobility"];
      selected_cancelation = resp["cancelation"];
      displayCancelation(resp["cancelation"]);
      displayCardMobilityChoice(resp["mobilityChoice"]);
      displayUserCard(resp["user"]);
      displayPartnerCard(resp["org"]);
      displayDocumentsCard(resp["docs"]);
      displayMobilityInfo(resp["mobility"]);
    },
    error: function (error) {
    }
  });
}

function displayCancelation(cancelation) {
  if (isNull(cancelation)) {
    $("#feedback_sign_document_warning").css('display', 'none');
    $("#other_reason").val("");
    $("#btn_confirm_cancelation").css('display', 'block');
  } else {
    $("#feedback_sign_document_warning").css('display', 'block');
    let getDate = selected_cancelation[0].dateCancellation;
    let canceler = (selected_user.idUser === selected_cancelation[0].idUser
        ? "Etudiant" : "Professeur");
    $("#other_reason").val(
        "Raison : " + cancelation[0].reason + "\n" +
        "Date : " + getDate.dayOfMonth + "/"
        + getDate.monthValue + "/" + getDate.year + "\n" +
        "Annulé par : " + canceler);
    $("#btn_confirm_cancelation").css('display', 'none');
  }
}

function displayMobilityInfo(mobility) {
  if (!isNull(mobility)) {
    if (mobility.mobilityTool) {
      $("#cb_mob_tool").prop('checked', true);
    }
    if (mobility.proEco) {
      $("#cb_pro_eco").prop('checked', true);
    }
    if (mobility.paymentSent) {
      $("#payement_info").html("Confirmer la 2ème demande de paiement");
    } else {
      $("#payement_info").html("Confirmer la 1ère demande de paiement");
    }
  }
}

function displayPartnerCard(partner) {
  if (partner !== undefined) {
    $("#card_org ").html(
        partner.businessName +
        "<p style='color: #6e707e; font-size:10px;'>"
        + "Type : " + getOrganisationType(partner.typeOrganisation) + "</p>");
  }
}

function displayDocumentsCard(docs) {
  if (isIterable(docs)) {
    for (item of docs) {
      let checkbox_id = "#cb_" + item.document;
      $(checkbox_id).prop('checked', true);
      //$(checkbox_id).prop('disabled', true);
    }
    let doc_dep = $("#departure_docs input[type='checkbox']:checked");
    let doc_ret = $("#return_docs input[type='checkbox']:checked");
    let total = (doc_dep.length + doc_ret.length);
    let rate = (total / 10) * 100;

    $("#card_docs ").html(rate + " % ");
    $("#progress_docs").css('width', rate + "%");
    $("#progress_docs").attr('aria-valuenow', 50);
    $("#card_state").html("<p style='color: #6e707e; font-size:10px;'>"
        + "Etat : " + getMobilityState(selected_mobility.mobilityState, "FR")
        + "</p>");
  }

}

function closeCheckBoxes(form) {
  $(form + " input[type='checkbox']").prop('checked', false);
  //$(form + " input[type='checkbox']").prop('disabled', false);
}

function selectNewDocuments() {

  let o = $("#div_mobility_information input[type='checkbox']:checked");

  if (isIterable(o)) {
    let tmp_docs = [];

    for (let i = 0; i < o.length; i++) {
      let i_doc = $(o[i]).val();
      if (!selected_docs.some(el => el.document === i_doc)) {
        tmp_docs.push(i_doc);
      }
    }

    let errors = [];

    if (tmp_docs.length === 0) {
      errors.push("Il n'y a aucun document à signer")
    }
    if (current_user.typeUser === "S") {
      errors.push(
          "Vous devez être connecté en tant que professeur afin de signer un document");
    }

    if (errors.length === 0) {
      $.ajax({
        type: 'POST',
        url: '/',
        data: {
          action: "signDocument",
          idMobilityChoice: selected_mobility_choice.idMobilityChoice,
          idUser: selected_mobility_choice.idUser,
          mobilityDocuments: JSON.stringify(tmp_docs)
        },
        success: function () {
          getAllDataRelatedToMobility(selected_mobility.idMobility);
          $("#feedback_sign_document_success").css('display', 'block');
          setTimeout(function () {
            $("#feedback_sign_document_success").addClass("animated fadeOut");
            setTimeout(function () {
              $("#feedback_sign_document_success").css('display', 'none');
            }, 1000);
          }, 3000);
          $("#feedback_sign_document_success").removeClass("animated fadeOut");
        },
        error: function (error) {
          errors.push(error);
        }
      });
    }
    if (errors.length > 0) {
      let html_errors = "";
      for (let i = 0; i < errors.length; i++) {
        html_errors += "<strong>Erreur 0" + (i + 1) + " : </strong>"
            + errors[i] + "<br/>";
      }
      $("#div_sign_document_fail ").html(html_errors);

      $("#feedback_sign_document_fail").css('display', 'block');
      setTimeout(function () {
        $("#feedback_sign_document_fail").addClass("animated fadeOut");
        setTimeout(function () {
          $("#feedback_sign_document_fail").css('display', 'none');
        }, 1000);
      }, 3000);
      $("#feedback_sign_document_fail").removeClass("animated fadeOut");
    }
  }
}

function confirmPayement(mobility) {
  let errors = [];
  if (mobility.paymentSent && mobility.mobilityState
      === EnumStateMobility.Completed) {
    errors.push("La mobilité a déjà été complété");
  }
  if (mobility.paymentSent && mobility.mobilityState
      !== EnumStateMobility.ToPaySold) {
    errors.push(
        "L'état de la mobilité doit etre (<strong>" + getMobilityState("tps",
        "FR")
        + "</strong>)");
  }
  if (current_user.typeUser === "S") {
    errors.push("Vous n'avez pas les doits de modification");
  }

  if (errors.length === 0) {
    $.ajax({
      type: 'POST',
      url: '/',
      data: {
        action: "confirmPayment",
        idMobilityChoice: mobility.idMobility,
        versionNumber: mobility.versionNumber,
      },
      success: function () {
        $("#feedback_sign_document_success").css('display', 'block');
        setTimeout(function () {
          $("#feedback_sign_document_success").addClass("animated fadeOut");
          setTimeout(function () {
            $("#feedback_sign_document_success").css('display', 'none');
          }, 1000);
        }, 3000);
        $("#feedback_sign_document_success").removeClass("animated fadeOut");
      },
      error: function (error) {
        errors.push(error);
      }
    });
  }

  if (errors.length > 0) {
    let html_errors = "";
    for (let i = 0; i < errors.length; i++) {
      html_errors += "<strong>Erreur 0" + (i + 1) + " : </strong>"
          + errors[i] + "<br/>";
    }
    $("#div_sign_document_fail ").html(html_errors);

    $("#feedback_sign_document_fail").css('display', 'block');
    setTimeout(function () {
      $("#feedback_sign_document_fail").addClass("animated fadeOut");
      setTimeout(function () {
        $("#feedback_sign_document_fail").css('display', 'none');
      }, 1000);
    }, 3000);
    $("#feedback_sign_document_fail").removeClass("animated fadeOut");
  }
}

function updateTools(mobility) {
  let errors = [];
  if (current_user.typeUser === "S") {
    errors.push("Vous n'avez pas les droits");
  }
  if (errors.length === 0) {
    $.ajax({
      type: 'POST',
      url: '/',
      data: {
        action: "updateTools",
        idMobilityChoice: mobility.idMobility,
        versionNumber: mobility.versionNumber,
        mobilityTool: $("#cb_mob_tool").prop('checked'),
        proEco: $("#cb_pro_eco").prop('checked'),
      },
      success: function (resp) {
        selected_mobility = resp;
        $("#feedback_sign_document_success").css('display', 'block');
        setTimeout(function () {
          $("#feedback_sign_document_success").addClass("animated fadeOut");
          setTimeout(function () {
            $("#feedback_sign_document_success").css('display', 'none');
          }, 1000);
        }, 3000);
        $("#feedback_sign_document_success").removeClass("animated fadeOut");
      },
      error: function (error) {
        errors.push(error);
      }
    });
  }

  if (errors.length > 0) {
    let html_errors = "";
    for (let i = 0; i < errors.length; i++) {
      html_errors += "<strong>Erreur 0" + (i + 1) + " : </strong>"
          + errors[i] + "<br/>";
    }
    $("#div_sign_document_fail ").html(html_errors);

    $("#feedback_sign_document_fail").css('display', 'block');
    setTimeout(function () {
      $("#feedback_sign_document_fail").addClass("animated fadeOut");
      setTimeout(function () {
        $("#feedback_sign_document_fail").css('display', 'none');
      }, 1000);
    }, 3000);
    $("#feedback_sign_document_fail").removeClass("animated fadeOut");
  }
}

function getCancelationReasons(mobility) {
  if (current_user.typeUser !== EnumMobilityConfirmation.Student) {
    $('#div_select_reasons').css('display', 'block');
    $.ajax({
      type: 'POST',
      url: '/',
      data: {
        action: "getCancelationReasons",
        idMobilityChoice: mobility.idMobility,
      },
      success: function (resp) {
        selected_reasons = resp;
        displayCancelationReasons(resp);
      },
      error: function (error) {
      }
    });
  } else {
    $("#div_select_reasons").css('display', 'none');
  }

}

function displayCancelationReasons(reasons) {
  let reasons_html = "";
  for (let reason of reasons) {
    reasons_html += "<option>" + reason.reason;
  }
  $("#select_reasons ").html(reasons_html);
}

function cancelMobility(mobility) {
  let errors = [];
  let reason = undefined;

  let select_reason = $("#select_reasons").val();
  let other_reason = $("#other_reason").val();

  if (select_reason !== null) {
    reason = select_reason;
  }
  if (!isNull(other_reason)) {
    reason = other_reason;
  }
  if (isNull(reason)) {
    errors.push("Vous devez mentionner une raison");
  }
  if (errors.length === 0) {
    $.ajax({
      type: 'POST',
      url: '/',
      data: {
        action: "cancelMobility",
        idMobilityChoice: mobility.idMobility,
        reason: reason,
      },
      success: function (resp) {
        selected_cancelation = resp;
        $("#feedback_sign_document_success").css('display', 'block');
        setTimeout(function () {
          $("#feedback_sign_document_success").addClass("animated fadeOut");
          setTimeout(function () {
            $("#feedback_sign_document_success").css('display', 'none');
          }, 1000);
        }, 3000);
        $("#feedback_sign_document_success").removeClass("animated fadeOut");
      },
      error: function (error) {
        errors.push(error);
      }
    });
  }

  if (errors.length > 0) {
    let html_errors = "";
    for (let i = 0; i < errors.length; i++) {
      html_errors += "<strong>Erreur 0" + (i + 1) + " : </strong>"
          + errors[i] + "<br/>";
    }
    $("#div_sign_document_fail ").html(html_errors);

    $("#feedback_sign_document_fail").css('display', 'block');
    setTimeout(function () {
      $("#feedback_sign_document_fail").addClass("animated fadeOut");
      setTimeout(function () {
        $("#feedback_sign_document_fail").css('display', 'none');
      }, 1000);
    }, 3000);
    $("#feedback_sign_document_fail").removeClass("animated fadeOut");
  }
}

function clearCurrentMobility() {
  selected_user = undefined;
  selected_mobility_choice = undefined;
  selected_docs = undefined;
  selected_partner = undefined;
  selected_mobility = undefined;
  selected_reasons = undefined;
  selected_cancelation = undefined;
}

function searchMobility() {
  let yearCriteria = $("#input_academic_year").val();
  let mobilityState = $("#select_mobility_state").val();
  if (mobilityState === "all") {
    mobilityState = "";
  }
  if (current_user.typeUser !== EnumMobilityConfirmation.Student) {
    $.ajax({
      type: 'POST',
      url: '/',
      data: {
        action: "researchMobility",
        yearCriteria: yearCriteria,
        stateCriteria: mobilityState
      },
      success: function (resp) {
        displayMobilities(resp);
      },
      error: function (error) {
      }
    });
  }
}
