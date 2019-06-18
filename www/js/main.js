let VIEW = "VIEW";
let HREF = "HREF";
let DEFAULT_AGE = 7;
let DEFAULT_VIEW = "#form_login";

let view = "";
let href = "";
let user_version_number;
let countries;
let users = [];
let current_user = "";
let mobility_choices = [];

let selected_user = undefined;
let selected_docs = undefined;
let selected_reasons = undefined;
let selected_partner = undefined;
let selected_mobility = undefined;
let selected_cancelation = undefined;
let selected_mobility_choice = undefined;

$(function () {

  if (getCookie(VIEW) === null) {
    setCookie(VIEW, DEFAULT_VIEW, DEFAULT_AGE);
  }

  refresh();
  getTypes();
  getPrograms();
  getCountries();

  /*
   * Redirection page
   */
  $(document).ready(function () {
    $("a").click(function (event) {
      event.preventDefault();

      let id = $(this).attr('id');
      switch (id) {

          /* REGISTER */
        case "href_register":
          setCookie(VIEW, ViewHome.REGISTER, DEFAULT_AGE);
          displayView(ViewHome.REGISTER);
          hideView(ViewHome.LOGIN);
          break;

          /* LOGIN */
        case "href_login":
          setCookie(VIEW, ViewHome.LOGIN, DEFAULT_AGE);
          $(displayView(ViewHome.LOGIN));
          $(hideView(ViewHome.REGISTER));
          break;

          /* CHOIX DE MOBILITES */
        case "href_home":
          href = $(this);
          setCookie(VIEW, ViewUser.HOME, DEFAULT_AGE);
          setCookie(HREF, id, DEFAULT_AGE);
          hideView(ViewHome.ALL);
          hideView(ViewUser.ALL);
          displayView(ViewUser.USER);
          displayView(ViewUser.ADD_MOBILITY_CHOICE);
          displayView(ViewUser.MOBILITY_CHOICES);
          $("#collapsePages a ").removeClass("active");
          href.addClass("active");
          break;

        case "href_add_new_mobility_choice":
          href = $(this);
          setCookie(VIEW, ViewUser.ADD_MOBILITY_CHOICE, DEFAULT_AGE);
          setCookie(HREF, id, DEFAULT_AGE);
          hideView(ViewUser.ALL);
          displayView(ViewUser.USER);
          displayView(ViewUser.ADD_MOBILITY_CHOICE);
          $("#collapsePages a ").removeClass("active");
          href.addClass("active");
          break;

        case "href_mobility_choices":
          href = $(this);
          setCookie(VIEW, ViewUser.MOBILITY_CHOICES, DEFAULT_AGE);
          setCookie(HREF, id, DEFAULT_AGE);
          hideView(ViewHome.ALL);
          hideView(ViewUser.ALL);
          displayView(ViewUser.USER);
          displayView(ViewUser.MOBILITY_CHOICES);
          $("#collapsePages a ").removeClass("active");
          href.addClass("active");
          break;

          /* MOBILITES */
        case "href_mobilities":
          href = $(this);
          setCookie(VIEW, ViewUser.MOBILITES, DEFAULT_AGE);
          setCookie(HREF, id, DEFAULT_AGE);
          clearCurrentMobility();
          hideView(ViewHome.ALL);
          hideView(ViewUser.ALL);
          displaySearch();
          displayView(ViewUser.USER);
          displayView(ViewUser.MOBILITES);
          v.a = "";
          v.a = "#" + id;
          $("#collapsePages a ").removeClass("active");
          href.addClass("active");
          break;

          /* USERS */
        case "href_encode_data":
          getUser();
          $("#div_btn_encode_data").css("display", "block");
          $("#div_btn_update_data").css("display", "none");
          href = $(this);
          setCookie(VIEW, ViewUser.ENCODE_DATA, DEFAULT_AGE);
          setCookie(HREF, id, DEFAULT_AGE);
          hideView(ViewHome.ALL);
          hideView(ViewUser.ALL);
          displayView(ViewUser.USER);
          displayView(ViewUser.ENCODE_DATA);
          $("#collapsePages a ").removeClass("active");
          href.addClass("active");
          break;

        case "href_users":
          href = $(this);
          setCookie(VIEW, ViewUser.INFOUSER, DEFAULT_AGE);
          setCookie(HREF, id, DEFAULT_AGE);
          hideView(ViewUser.ALL);
          displaySearch();
          displayView(ViewUser.USER);
          displayView(ViewUser.INFOUSER);
          v.a = "";
          v.a = "#" + id;
          $("#collapsePages a ").removeClass("active");
          href.addClass("active");
          break;

        case "href_partners":
          href = $(this);
          setCookie(VIEW, ViewUser.PARTNERS, DEFAULT_AGE);
          setCookie(HREF, id, DEFAULT_AGE);
          hideView(ViewUser.ALL);
          displaySearch();
          displayView(ViewUser.USER);
          displayView(ViewUser.PARTNERS);
          getAndDisplayAllPartners();
          $("#collapsePages a ").removeClass("active");
          href.addClass("active");
          break;

        case "href_add_partners":
          href = $(this);
          setCookie(VIEW, ViewUser.ADD_PARTNERS, DEFAULT_AGE);
          setCookie(HREF, id, DEFAULT_AGE);
          hideView(ViewUser.ALL);
          displayView(ViewUser.USER);
          displayView(ViewUser.ADD_PARTNERS);
          getAndDisplayAllPartners();
          $("#collapsePages a ").removeClass("active");
          href.addClass("active");
          break;
      }
    });
  });

  /*
   * Listener on the view to
   * Load contents for a specific view
   */
  v.registerListener(function (val) {
    switch (val) {
      case "#form_user":
        getOrganisations();
        getAllMobilityChoices();
        getUser();
        getAllUsers();
        getAllMobilityChoices();
        break;

      case "#href_users":
        getAndDisplayAllUsers();
        break;

      case "#div_users":
        getAndDisplayAllUsers();
        break;

      case "#href_mobilities":
        getMobilites();
        break;

      case "#href_partners":
        getAndDisplayAllPartners();
        break;
    }
  });

});

function displayView(v) {
  if (v === ViewUser.ALL) {
    displayView(ViewUser.ENCODE_DATA);
    displayView(ViewUser.MOBILITY_CHOICES);
    displayView(ViewUser.ADD_MOBILITY_CHOICE);
    displayView(ViewUser.INFOUSER);
    displayView(ViewUser.MOBILITES);
    displayView(ViewUser.MOBILITY_INFO);
    displayView(ViewUser.USER);
    displayView(ViewUser.MOBILITY_SEARCH);
    displayView(ViewUser.PARTNER_SEARCH);
    displayView(ViewUser.USER_SEARCH);
    displayView(ViewUser.PARTNERS);
    displayView(ViewUser.ADD_PARTNERS);
    displayView(ViewUser.USER_STATUS);
  } else if (v === ViewHome.ALL) {
    displayView(ViewHome.REGISTER);
    displayView(ViewHome.LOGIN);
  }
  $(v).css('display', 'block');
}

function hideView(v) {
  if (v === ViewUser.ALL) {
    hideView(ViewUser.ENCODE_DATA);
    hideView(ViewUser.MOBILITY_CHOICES);
    hideView(ViewUser.ADD_MOBILITY_CHOICE);
    hideView(ViewUser.INFOUSER);
    hideView(ViewUser.MOBILITES);
    hideView(ViewUser.MOBILITY_INFO);
    hideView(ViewUser.USER);
    hideView(ViewUser.MOBILITY_SEARCH);
    hideView(ViewUser.PARTNER_SEARCH);
    hideView(ViewUser.USER_SEARCH);
    hideView(ViewUser.PARTNERS);
    hideView(ViewUser.ADD_PARTNERS);
    hideView(ViewUser.USER_STATUS);
  } else if (v === ViewHome.ALL) {
    hideView(ViewHome.REGISTER);
    hideView(ViewHome.LOGIN);
  }
  $(v).css('display', 'none');
}

function displaySearch() {

  hideView(ViewUser.MOBILITY_SEARCH);
  hideView(ViewUser.USER_STATUS);
  hideView(ViewUser.USER_SEARCH);
  hideView(ViewUser.PARTNER_SEARCH);

  if (current_user.typeUser !== EnumMobilityConfirmation.Student) {
    displayView(EnumHref.USERS);
  }

  switch (getCookie(VIEW)) {

    case "#div_mobilites":
      if (current_user.typeUser === EnumMobilityConfirmation.Student) {
      } else {
        displayView(ViewUser.MOBILITY_SEARCH);
      }
      break;

    case "#div_partners":
      displayView(ViewUser.PARTNER_SEARCH);
      break;

    case "#div_users":
      if (current_user.typeUser !== EnumMobilityConfirmation.Student) {
        displayView(ViewUser.USER_SEARCH);
      }
      if (current_user.typeUser === EnumMobilityConfirmation.Reponsible) {
        displayView(ViewUser.USER_STATUS);
      }
      break;
  }
}
