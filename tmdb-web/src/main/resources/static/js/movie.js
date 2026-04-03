$().ready(function () {
  var fileInput = $("<input />");
  fileInput.attr({
    type: "file",
    name: "attachPoster",
  });

  // 유효성 검사
  $("#writeVO").on("submit", function (event) {
    event.preventDefault();

    $(this).find(".validation-error").remove();

    // 하나라도 안돼면
    var title = $("#title").val();
    if (!title) {
      var titleErrorMessage = $("<div>");
      titleErrorMessage.addClass("validation-error");
      titleErrorMessage.text("영화 제목을 작성해주세요.");
      $("#title").after(titleErrorMessage);
    }

    var synopsis = $("#synopsis").val();
    if (!synopsis) {
      var synopsisErrorMessage = $("<div>");
      synopsisErrorMessage.addClass("validation-error");
      synopsisErrorMessage.text("시놉시스를 작성해주세요.");
      $("#synopsis").after(synopsisErrorMessage);
    }
    var movieState = $("#movieState").val();
    if (!movieState.length > 6 || movieState.length == 0) {
      var movieStateErrorMessage = $("<div>");
      movieStateErrorMessage.addClass("validation-error");
      movieStateErrorMessage.text("개봉 상태를 5글자 이하로 작성해주세요.");
      $("#movieState").after(movieStateErrorMessage);
    }
    var language = $("#language").val();
    if (!language.length > 7 || language.length == 0) {
      var languageErrorMessage = $("<div>");
      languageErrorMessage.addClass("validation-error");
      languageErrorMessage.text("영화의 원어를 작성해주세요. 최대 6글자입니다.");
      $("#language").after(languageErrorMessage);
    }

    var runningTime = $("#runningTime");
    var runningTimeVal = runningTime.val();
    if(!runningTimeVal){
        runningTime.val("0");
    }
    
    var budget = $("#budget");
    var budgetVal = budget.val();
    if(!budgetVal){
        budget.val("0");
    }
    

    var profit = $("#profit");
    var profitVal = profit.val();
    if(!profitVal){
        profit.val("0");
    }
    
    /*
    var runningTime = $("#runningTime");
    var runningTimeVal = runningTime.val();
    if (!runningTimeVal) {
        var runningTimeErrorMessage = $("<div>");
        runningTimeErrorMessage.addClass("validation-error");
        runningTimeErrorMessage.text("입력할 값이 없다면 0을 입력해주세요.");
        $("#runningTime").after(runningTimeErrorMessage);
    }

    var budget = $("#budget");
    var budgetVal = budget.val();
    if (!budgetVal) {
        var budgetErrorMessage = $("<div>");
        budgetErrorMessage.addClass("validation-error");
        budgetErrorMessage.text("입력할 값이 없다면 0을 입력해주세요.");
        $("#budget").after(budgetErrorMessage);
    }


    var profit = $("#profit");
    var profitVal = profit.val();
    if (!profitVal) {
        var profitErrorMessage = $("<div>");
        profitErrorMessage.addClass("validation-error");
        profitErrorMessage.text("입력할 값이 없다면 0을 입력해주세요.");
        $("#profit").after(profitErrorMessage);
    }
    */
    
    // 다 잘됨
    if ($(".validation-error").length === 0) {
      this.submit();
    }
  });
});
