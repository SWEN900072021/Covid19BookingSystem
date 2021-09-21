<%@ page import="com.example.covid19bookingsystem.domain.VaccineType" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.covid19bookingsystem.domain.Question" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <title>Vaccine Questionnaire</title>
    <%
        String vaccineType = (String) request.getSession().getAttribute("vaccineType");

        ArrayList<Question> questions = (ArrayList<Question>) request.getSession().getAttribute("questionsToDisplay");
        ArrayList<String> questionStrings = new ArrayList<>();

        for (Question question: questions) {

            String questionString = question.getQuestion();
            questionStrings.add(questionString);
        }
    %>
</head>
<body>
    <h4
            class="display-4"
            style="display: flex;justify-content: center;margin-top: 20px;font-size: 45px"
    >
        Vaccine Questionnaire
    </h4>
    <br/>
    <br/>
    <div class="card text-center border-secondary mb-3" style="width: 50rem;margin: 0 auto;float: none;margin-bottom: 10px;">
        <div class="card-body">
            <form action = "vaccineQuestionnaire" method = "post">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <input type="hidden"
                       name="vaccineType"
                       value="<%= vaccineType%>"/>
                <%
                    if(questionStrings.isEmpty()){
                %>
                <div class="form-group row">
                    <label class="col-sm-12">
                        No requirements needed for this vaccine, you may freely proceed to booking your timeslot.
                    </label>
                    <br>
                </div>
                <%
                    } else {

                        int questionIterator = 0;
                        for (String question: questionStrings) {
                            questionIterator++;

                %>
                <div class="row">
                    <label class="col-sm-5 col-form-label" for="questions"> <strong> Question <%= questionIterator%>: </strong></label>
                    <div class="col-sm-7" style="text-align: left">
                        <div id="questions">
                            <div>
                                <%= question %>
                                <select id="vaccineQuestionAnswer"
                                        name = 'vaccineQuestionAnswer<%= questionIterator%>'>
                                    <option value="false">No</option>
                                    <option value="true">Yes</option>
                                </select>
                            </div>

                        </div>
                    </div>
                </div>
                <%
                        }
                    }
                %>

                <div class="form-group row">
                    <div class="col-sm-12">
                        <button type="submit" class="btn btn-outline-dark"> Next </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
</html>