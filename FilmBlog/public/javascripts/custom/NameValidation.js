/**
 * Created by boris on 11/20/15.
 */
function checkName(field) {
    $(document).ready(function () {
        $name = $("#validate-name").val();
        $surname = $("#validate-surname").val();
        if (1 == field) {
            $targetDiv = $("#name");
        } else {
            $targetDiv = $("#surname");
        }
        if (isValidName($name, field, $surname)) {
            $targetDiv.attr("class", "form-group has-success");
            if (2 == field) {
                $button = $("#enable-button").prop("disabled", false);
            }
        } else {
            $targetDiv.attr("class", "form-group has-error");
        }
    })
};

function isValidName(name, field, surname) {
    if (1 == field) {
        if (name.length > 2 && name.length < 41)
            return true;
    } else {
        console.log("adasd");
        if (surname.length > 2 && surname.length < 41) {
            return true;
        }
    }
    return false;
};