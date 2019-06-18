function refresh() {
  $.ajax({
    type: 'POST',
    url: '/',
    data: {
      action: "whoami"
    },
    success: function () {
      $(hideView(ViewHome.LOGIN));
      $(hideView(ViewUser.ALL));
      $(displayView(ViewUser.USER));
      v.a = "#form_user";

      /* Set Current view */
      if (getCookie(VIEW) === null) {
        setCookie(VIEW, ViewUser.USER, DEFAULT_AGE);
      } else {
        if (getCookie(VIEW) === ViewUser.HOME) {
          displayView(ViewUser.USER);
          displayView(ViewUser.ADD_MOBILITY_CHOICE);
          displayView(ViewUser.MOBILITY_CHOICES);
        }
        view = getCookie(VIEW);
      }
      if (view !== ViewUser.HOME) {
        $(displayView(view));
      }

      /* Reset listener */
      v.a = "";

      /* Set Current href*/
      let current_href = getCookie(HREF);
      if (current_href !== null) {
        $("#collapsePages a ").removeClass("active");
        current_href = "#" + current_href;
        v.a = current_href;
        $(current_href).addClass("active");
      }
      displaySearch();
    },
    error: function () {
      $(hideView(ViewUser.ALL));
      $(displayView(ViewHome.LOGIN));
    }
  });
}
