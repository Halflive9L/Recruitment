$(document).ready(function(){
  // Delegating to `document` just in case.
  $(document).on("hidden.bs.modal", "#filesModal", function () {
    $(this).html(""); // Just clear the contents.
  });
});
