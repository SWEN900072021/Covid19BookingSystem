<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <title>Add Vaccine Type</title>
</head>
<body>
<h4
        class="display-4"
        style="display: flex;justify-content: center;margin-top: 20px;font-size: 45px"
>
    Add Vaccine Type
</h4>
<br/>
<div class="card text-center border-secondary mb-3" style="width: 50rem;margin: 0 auto;float: none;margin-bottom: 10px;">
    <div class="card-body">
        <form action = "addVaccineType" method = "post">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <div class="form-group row">
                <label for="nameId" class="col-sm-4 col-form-label"><strong>Name:</strong></label>
                <div class="col-sm-8">
                    <input
                            type="text"
                            class="form-control"
                            id="nameId"
                            name = "name"
                    >
                </div>
                <br>
                <br>
                <div class="col-sm-5 col-form">
                    <div id="certificationtog">
                        <input type="button" id="addquestions" name="addQuestions" class="btn btn-outline-dark"
                               value="Add Question" onclick="addQuestion();">
                        <br>
                    </div>
                </div>
            </div>
            <div id="questions"></div>
            <div id="hiddenDiv"></div>
            <div id="div1" class="col-sm-5 col-form"> </div>
            <div class="form-group row">
                <div class="col-sm-12">
                    <button type="submit"
                            class="btn btn-outline-dark"
                            onclick=submitQuestionNumber()
                    >Submit Vaccine Type</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>

<script>
    var question_i;
    question_i = 0;

    function addQuestion() {

        var divRow = document.createElement("div");
        divRow.setAttribute("class", "form-group row");

        var questionLabel = document.createElement("label");
        questionLabel.setAttribute("class", "col-sm-4 col-form-label");
        questionLabel.setAttribute("style", "left: 13px");
        questionLabel.setAttribute("id", "inputQuestion");
        questionLabel.innerHTML = ' <strong>Question: </strong>';

        var divInput = document.createElement("div");
        divInput.setAttribute("class", "col-sm-8");


        var questionInput = document.createElement("input");
        questionInput.setAttribute("type", "text");
        questionInput.setAttribute("class", "form-control");
        questionInput.setAttribute("id", "inputQuestion");

        questionInput.setAttribute("name", 'question' + question_i);

        var successAnswerLabel = document.createElement("label");
        successAnswerLabel.setAttribute("class", "col-sm-5 col-form-label");
        successAnswerLabel.setAttribute("style", "left: 9px");
        successAnswerLabel.innerHTML = "<strong>Success Answer:</strong>";

        var br = document.createElement("br");
        br.innerHTML = "<br>";

        var select = document.createElement("select");
        select.setAttribute("name", 'selectOption' + question_i);
        select.setAttribute("id", 'selectOption' + question_i);
        select.setAttribute("class", "col-sm-2 form-control");
        select.setAttribute("style", "left: -50px");
        select.setAttribute("value", "true");

        var optionFalse = document.createElement("option");
        optionFalse.setAttribute("name", 'successQuestion' + question_i);
        optionFalse.setAttribute("id", 'successQuestion' + question_i);
        optionFalse.setAttribute("value", "false");
        optionFalse.innerHTML = "No";

        var optionTrue = document.createElement("option");
        optionTrue.setAttribute("name", 'successQuestion' + question_i);
        optionTrue.setAttribute("id", 'successQuestion' + question_i);
        optionTrue.setAttribute("value", "true");
        optionTrue.innerHTML = "Yes";

        var questionBlock = document.getElementById("questions");
        questionBlock.append(divRow);
        divRow.appendChild(questionLabel);
        divRow.appendChild(divInput);
        divInput.appendChild(questionInput);
        divInput.append(br);
        divRow.appendChild(successAnswerLabel);
        divRow.appendChild(select);
        select.appendChild(optionFalse);
        select.appendChild(optionTrue);

        question_i++;
    }

    function submitQuestionNumber() {

        var hiddenInput = document.createElement("input");
        hiddenInput.setAttribute("type", "hidden");
        hiddenInput.setAttribute("id", "questionNumber");
        hiddenInput.setAttribute("name", "questionNumber");
        hiddenInput.setAttribute("value", String(question_i));

        var hiddenBlock = document.getElementById("hiddenDiv");
        hiddenBlock.appendChild(hiddenInput);
    }
</script>
