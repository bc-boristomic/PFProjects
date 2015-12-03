/**
 * Created by boris on 12/3/15.
 */
function checkPrice(articleId) {
    $date = $('#date').val();
    $.ajax({
        type: 'post',
        url:'/reports/date',
        data: "selected=" + $date +"&id=" + articleId
    }).success(function(response) {
        $('#currentPrice').attr('value', response);
    }).error(function(response) {
        $('#currentPrice').attr('value', 'No price for selected date.');
    })
};