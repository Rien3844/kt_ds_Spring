$().ready(function () {
  // add-file을 클릭하면
  // 새로운 파일 인풋과 버튼을
  // .attach-files 아래에 추가한다.
  $(".attach-files").on("click", ".add-file", function () {
    //   $(".add-file").on("click", function () {
    // 새로운 파일이 추가될 때 마다 기존의 "add-file"을 "del-file"로 변경하고
    // text는 "+"에서 "-"로 변경한다.
    $(this /*클릭한 주체*/)
      .closest(".attach-files")
      .children(".add-file")
      .removeClass(".add-file")
      .addClass(".del-file")
      .text("-")
      .off("click") // 할당 되어있던 이벤트 제거.
      .on("click", function () {
        // 버튼 왼쪽에 있는 인풋 태그 삭제.
        $(this).prev().remove();
        // 버튼도 삭제
        $(this).remove();
      }); // 새로운 이벤트 추가.

    var fileInput = $("<input />");
    fileInput.attr({
      type: "file",
      name: "attachFile",
    });

    var addButton = $("<button/>");
    addButton.attr("type", "button").addClass("add-file").text("+");

    $(".attach-files").append(fileInput).append(addButton);
  });

  // 유효성 검사
  $("#writeVO").on("submit", function (event) {
    event.preventDefault();

    $(this).find(".validation-error").remove();

    // 하나라도 안될때
    var subject = $("#subject").val();
    if (!subject.length < 3) {
      var subjectErrorMessage = $("<div>");
      subjectErrorMessage.addClass("validation-error");
      subjectErrorMessage.text("제목은 3글자 이상이어야합니다.");
      $("#subject").after(subjectErrorMessage);
    }

    var email = $("#email").val();
    if (!email) {
      var emailErrorMessage = $("<div>");
      emailErrorMessage.addClass("validation-error");
      emailErrorMessage.text("이메일 형태가 아닙니다.");
      $("#email").after(emailErrorMessage);
    }

    // 다 될때
    if ($(".validation-error").length === 0) {
      this.submit();
    }
  });
});
