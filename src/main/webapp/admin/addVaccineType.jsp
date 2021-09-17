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
                    <br>
                    <div class="col-sm-5 col-form">
                        <div id="certificationtog">
                            <input type="button" id="addquestions" name="addQuestions" class="btn btn-outline-dark"
                                   value="Add Question" onclick="addQuestion();">
                            <br>
                            <div id="questions"></div>
                        </div>
                    </div>
                </div>

                <div id="hiddenDiv"></div>

                <div id="div1" class="col-sm-5 col-form"> </div>
                <div class="form-group row">
                    <div class="col-sm-12">
                        <button type="submit"
                                class="btn btn-outline-dark"
                                onsubmit=submitQuestionNumber()
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

        var question = document.createElement("label");
        question.innerHTML = '<br> <strong> Question: </strong> <br>';
        question.setAttribute("type", "text");
        question.setAttribute("class", "col-sm-16");

        var div2 = document.createElement("div");
        div2.setAttribute("width", "600px");

        var div1 = document.createElement("div");
        div1.innerHTML = "<br>";


        var inputElement1 = document.createElement("input");
        inputElement1.setAttribute("type", "text");
        inputElement1.setAttribute("class", "col-sm-16");
        inputElement1.setAttribute("width", "100px");
        inputElement1.setAttribute("id", "inputQuestion");
        inputElement1.setAttribute("name", 'question' + question_i);
        console.log('question' + question_i);

        var successAnswer = document.createElement("label");
        successAnswer.innerHTML = "<strong> Success Answer:</strong>";
        successAnswer.setAttribute("class", "col-sm-16");

        // selection for yes/no
        var select = document.createElement("select");
        select.setAttribute("name", 'selectOption' + question_i);
        select.setAttribute("id", 'selectOption' + question_i);
        select.setAttribute("value", "true");

        var optionTrue = document.createElement("option");
        optionTrue.setAttribute("name", 'successQuestion' + question_i);
        optionTrue.setAttribute("id", 'successQuestion' + question_i);
        optionTrue.setAttribute("value", "true");
        optionTrue.innerHTML = "Yes";

        var optionFalse = document.createElement("option");
        optionFalse.setAttribute("name", 'successQuestion' + question_i);
        optionFalse.setAttribute("id", 'successQuestion' + question_i);
        optionFalse.setAttribute("value", "false");
        optionFalse.innerHTML = "No";

        var hiddenInput = document.createElement("input");
        hiddenInput.setAttribute("type", "hidden");
        inputElement1.setAttribute("id", "questionNumber");
        inputElement1.setAttribute("name", String.valueOf(question_i));
        console.log('question' + question_i);

        var questionBlock = document.getElementById("div1");
        questionBlock.appendChild(div2);
        div2.appendChild(question);
        div2.appendChild(inputElement1);
        div2.appendChild(div1);
        div2.appendChild(successAnswer);
        div2.appendChild(div1);
        div2.appendChild(select);
        select.appendChild(optionTrue);
        select.appendChild(optionFalse);
        questionBlock.appendChild(div1);

        question_i++;
    }

    function submitQuestionNumber() {

        var x = document.createElement("input");
        x.setAttribute("type", "hidden");
        x.setAttribute("name", "questionNumber");
        x.setAttribute("id", "questionNumber");
        x.setAttribute("value", ""+ question_i);

        var hiddenBlock = document.getElementById("hiddenDiv");
        hiddenBlock.appendChild(x);

    }
</script>
