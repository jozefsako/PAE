$(function () {
  $("#btn_add_partner").on("click", function (event) {
    event.preventDefault();
    addPartners();
  });

  $("#searchPartner_button").on("click", function (event) {
    event.preventDefault();
    searchPartner();
    displayView(ViewUser.PARTNERS);
  });

  $(".col-sm-4").css("border-color", "red");

  /* Btn info partner : consulter toutes les infos */
  $("#tbody_partners").on("click", "tr a", function (event) {
    event.preventDefault();
    let tr = $(this).parent().parent();
    let id_partner = tr.attr('id');
    viewPartner(id_partner);
  });

  /* Onclick btn return partner */
  $("#btn_return_partner").on("click", function (event) {
    event.preventDefault();

    /* reset input form */
    $(':input', '#form_add_partner')
    .not(':button, :submit, :reset, :hidden')
    .val('')
    .removeAttr('checked')
    .removeAttr('selected');

    /* reset btns */
    hideView(ViewUser.ADD_PARTNERS);
    displayView(ViewUser.PARTNERS);
    $("#div_btn_add_partner").css("display", "block");
    $("#div_btn_return_partner").css("display", "none");
  });
});

function viewPartner(idPartner) {
  $.ajax({
    type: 'POST',
    url: '/',
    data: {
      action: "viewOrganisation",
      idOrganisation: idPartner,
    },
    success: function (resp) {
      hideView(ViewUser.PARTNERS);
      hideView(ViewUser.PARTNER_SEARCH);
      displayView(ViewUser.ADD_PARTNERS);
      JSONToForm(resp, ViewUser.ADD_PARTNERS);
      $("#div_btn_add_partner").css("display", "none");
      $("#div_btn_return_partner").css("display", "block");
    }
  });
}

function addPartners() {
  $("#div_btn_add_partner").css("display", "block");
  const emailRegex = /^([A-Za-z0-9_\-.+])+@([A-Za-z0-9_\-.])+\.([A-Za-z]{2,})$/;
  if ($('#legalName').val() === "" ||
      $('#departement').val() === "" || $('#typeOrganisation').val() === ""
      || $('#numberOfEmployees').val() === "" ||
      $('#legalAddress').val() === "" || $("#country").val() === "" || $(
          '#region').val() === "" ||
      $('#postCode').val() === "" || $('#city').val() === "" || $(
          '#email').val() === "" ||
      $('#webSite').val() === "" || $('#telephone1').val() === ""
  ) {
    $("#feedback_add_partner_fail").css('display', 'block');
    $("#feedback_add_partner_success").css('display', 'none');
    $("#feedback_add_partner_fail_email").css('display', 'none');
  } else if (!emailRegex.test($('#email').val())) {
    $("#feedback_add_partner_fail").css('display', 'none');
    $("#feedback_add_partner_success").css('display', 'none');
    $("#feedback_add_partner_fail_email").css('display', 'block');
  } else {
    let objectJson = FormToJSON($('#form_add_partner'));
    $.ajax({
      type: 'POST',
      url: '/',
      data: {
        action: "addOrganisation",
        versionNumber: user_version_number,
        json: objectJson
      },
      success: function (resp) {
        $("#feedback_add_partner_success").css('display', 'block');
        $("#feedback_add_partner_fail").css('display', 'none');
        $("#feedback_add_partner_fail_email").css('display', 'none');
        $(':input', '#form_add_partner')
        .not(':button, :submit, :reset, :hidden')
        .val('')
        .removeAttr('checked')
        .removeAttr('selected');
      },
      error: function () {

      }
    });
  }

}

function getAndDisplayAllPartners() {
  let legalName = "";
  let country = "";
  let city = "";
  $.ajax({
    type: 'POST',
    url: '/',
    data: {
      action: "researchOrganisations",
      nameCriteria: legalName,
      countryCriteria: country,
      cityCriteria: city
    },
    success: function (resp) {
      displayPartners(resp);
    },
    error: function (error) {
    }
  });
}

function displayPartners(organisations) {
  if (isIterable(organisations)) {
    let rows = "";
    for (let org of organisations) {
      rows += "<tr id='" + org.idOrganisation + "'>";
      rows += "<td>" + org.legalName;
      rows += "<td>" + org.departement;
      rows += "<td>" + org.email;
      rows += "<td>" + org.country;
      rows += "<td>" + org.city;
      rows += "<td>" + org.typeOrganisation;
      rows += "<td>" + infoButton();
    }
    $("#tbody_partners").html(rows);
  } else {

  }
}

function searchPartner() {
  let legalName = $("#input_name_partner").val();
  let country = $("#input_country_partner").val();
  let city = $("#input_city_partner").val();
  $.ajax({
    type: 'POST',
    url: '/',
    data: {
      action: "researchOrganisations",
      nameCriteria: legalName,
      countryCriteria: country,
      cityCriteria: city
    },
    success: function (resp) {
      displayPartners(resp);
    },
    error: function (error) {
    }
  });
}
