(function() {
  var today = new Date();
  var year = today.getFullYear();
  var month = today.getMonth() + 1;
  var date = today.getDate();
  function createOption(id, startNum, endNum, current) {
    var selectDom = document.getElementById(id);
    var optionDom = '';
    for (var i = startNum; i <= endNum; i++) {
      var option = '';
      if (i === current) {
        option = '<option value="' + i + '">' + i + '</option>';
      } else {
        option = '<option value="' + i + '" selected>' + i + '</option>';
      }
      optionDom += option;
    }
    selectDom.insertAdjacentHTML('beforeend', optionDom);
  }
  createOption('year', 1900, year, year);
  createOption('month', 1, 12, month);
  createOption('date', 1, 31, date);
})()