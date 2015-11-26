/**
 * Created by boris on 11/20/15.
 */
function checkEmail(fromWhere) {
    $(document).ready(function () {
        $targetDiv = $("#email");
        $emailValue = $("#validate-email").val();
        if (isValidEmailAddress($emailValue)) {
            $.ajax({
                method: "get",
                url: ("login" == fromWhere ? "/checkEmailLog" : "/checkEmailReg"),
                data: "value=" + $emailValue
            }).success(function (response) {
                $targetDiv.attr("class", "form-group has-success");
            }).error(function (response) {
                $targetDiv.attr("class", "form-group has-error");
            })
        } else {
            $targetDiv.attr("class", "form-group has-error");
        }
    })
};

function isValidEmailAddress(emailAddress) {
    var pattern = new RegExp("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
    return pattern.test(emailAddress);
};