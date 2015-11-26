/**
 * Created by boris on 11/20/15.
 */
function checkPassword(field, fromWhere) {
    $(document).ready(function () {
        $passwordValue = $("#validate-password").val();
        $passwordValue2 = $("#validate-password2").val();
        if (1 == field) {
            $targetDiv = $("#password");
        } else {
            $targetDiv = $("#password2");
        }
        if (isValidPassword($passwordValue, field, $passwordValue2)) {
            $targetDiv.attr("class", "form-group has-success");
            if ("login" == fromWhere) {
                $button = $("#enable-button").prop("disabled", false);
            }
        } else {
            $targetDiv.attr("class", "form-group has-error");
        }
    })
};

function isValidPassword(password, fromWhere, password2) {
    var pattern = new RegExp("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})");
    if (1 == fromWhere) {
        return pattern.test(password);
    }
    if (password == password2) {
        return pattern.test(password2);
    }
};