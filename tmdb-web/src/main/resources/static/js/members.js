$().ready(function () {

  $("#email").on("keyup", function () {
    var emailValue = $(this).val();

    var emailPattern =
      /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/;
    // 이메일 중복체크
    // 이메일을 입력했을 때
    if (emailPattern.test(emailValue)) {
      // 비동기로 중복 여부를 검사해 온다.
      fetch("/regist/check/duplicate/" + emailValue)
        // ==> 비동기 결과를 이용해서 메시지를 노출하거나 숨김.
        .then(function (feachResult) {
          return feachResult.json();
        })
        .then(function (json) {
          var duplicateResult = $("#email")
            .closest(".input-div")
            .children(".validation-error");

          if (duplicateResult.length === 0) {
            duplicateResult = $("#email")
              .closest(".input-div")
              .children(".validation-ok");
          }

          if (duplicateResult.length === 0) {
            duplicateResult = $("<div>");
            $("#email").after(duplicateResult);
          }

          if (!json.duplicate) {
            // 중복이 안된다
            // ==> 사용 가능한 이메일
            duplicateResult.removeClass("validation-error");
            duplicateResult.addClass("validation-ok");
            duplicateResult.text(json.email + "은 사용 가능합니다.");
          } else {
            // 중복이 된다
            // 사용 불가능한 이메일
            duplicateResult.removeClass("validation-ok");
            duplicateResult.addClass("validation-error");
            duplicateResult.text(json.email + "은 이미 사용중입니다.");
          }
        });
    } else {
      $(this)
        .closest("input.div")
        .children(".validation-ok, .validation-error")
        .remove();
    }
  });

  $("#confirm-password, #password").on("keyup", function () {
    var confirmPasswordValue = $("#confirm-password").val();
    var passwordValue = $("#password").val();

    $("#password, #confirm-password")
      .closest(".input-div")
      .children(".validation-error")
      .remove();

    if (confirmPasswordValue != passwordValue) {
      var passwordErrorMessage = $("<div>");
      var confirmPasswordErrorMessage = $("<div>");

      confirmPasswordErrorMessage.addClass("validation-error");
      confirmPasswordErrorMessage.text("비밀번호가 일치하지 않습니다.");
      passwordErrorMessage.addClass("validation-error");
      passwordErrorMessage.text("비밀번호가 일치하지 않습니다.");
      $("#confirm-password").after(confirmPasswordErrorMessage);
      $("#password").after(passwordErrorMessage);
    }
  });

  $("#show-password").on("change", function () {
    var checked = $(this).prop("checked");
    if (checked) {
      $("#password").attr("type", "text");
    } else {
      $("#password").attr("type", "password");
    }
  });
  $("#registVO").on("submit", function (event) {
    event.preventDefault();

    // form 내부에 존재하는 ".validation-error" 엘리먼트를 모두 제거.
    $(this).find(".validation-error").remove();

    $("#password").trigger("keyup");

    // 이름, 이메일, 비밀번호 중 하나 이상을 제대로 입력하지 않았다 ==> 에러 메시지를 화면에 보여준다.(form 전송 X)
    var email = $("#email").val();
    if (!email) {
      var emailErrorMessage = $("<div>");
      emailErrorMessage.addClass("validation-error");
      emailErrorMessage.text("이메일 형태가 아닙니다.");
      $("#email").after(emailErrorMessage);
    }

    var name = $("#name").val();
    if (!name) {
      var nameErrorMessage = $("<div>");
      nameErrorMessage.addClass("validation-error");
      nameErrorMessage.text("이름을 입력해주세요.");
      $("#name").after(nameErrorMessage);
    }

    var passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/;
    var password = $("#password").val();
    if (!passwordPattern.test(password)) {
      var passwordErrorMessage = $("<div>");
      passwordErrorMessage.addClass("validation-error");
      passwordErrorMessage.text(
        "비밀번호는 영소문자, 영대문자, 숫자 최소 1개를 포함하여 8글자 이상으로 입력하세요.",
      );
      $("#password").after(passwordErrorMessage);
    }

    // 이름과 이메일과 비밀번호를 제대로 입력했다 ==> form 전송.
    /* 에러 메시지가 단 한개도 없다면*/
    if ($(".validation-error").length === 0) {

      this.submit(); // ==> JavaScript Event
    }
  });
});
