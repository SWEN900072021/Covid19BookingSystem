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
<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
    <symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16">
        <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
    </symbol>
</svg>
<h4
        class="display-4"
        style="display: flex;justify-content: center;margin-top: 20px;font-size: 45px"
>
    Add Vaccine Type
</h4>
<br/>
<div class="alert alert-primary d-flex align-items-center" role="alert"
     style="width: 50rem;margin: 0 auto;float: none;margin-bottom: 10px;">
    <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Info:"><use xlink:href="#info-fill"/></svg>
    <div style="margin-left: 15px;">
        Use <strong>Add Question</strong> to sequentially add questions that vaccine recipients must answer to be <br/>
        eligible for the new vaccine type along with their success answers. (Can add multiple questions)
    </div>
</div>
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
                        <input type="button" id="addquestions" name="addQuestions" class="btn btn-dark"
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
                            class="btn btn-dark"
                            onclick=submitQuestionNumber()
                    >Submit Vaccine Type</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div >
    <form name="return_home" method="get" action="home"
          style="position: absolute;bottom: 0;left: 1%;">
        <div class="form-group row">
            <div class="col-sm-12">
                <button type="submit" class="btn btn btn-dark">Return Home</button>
            </div>
        </div>
    </form>
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
