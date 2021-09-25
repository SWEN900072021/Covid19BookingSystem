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
    <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
        <symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16">
            <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
        </symbol>
    </svg>
    <h4
            class="display-4"
            style="display: flex;justify-content: center;margin-top: 20px;font-size: 45px"
    >
        Vaccine Questionnaire
    </h4>
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
                <div class="alert alert-primary d-flex align-items-center" role="alert"
                     style="width: 40rem;margin: 0 auto;float: none;margin-bottom: 10px;">
                    <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Info:"><use xlink:href="#info-fill"/></svg>
                    <div>
                        No requirements needed for this vaccine, you may freely proceed to booking your timeslot.
                    </div>
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
                <br/>
                <div class="form-group row">
                    <div class="col-sm-12">
                        <button type="submit" class="btn btn-dark"> Next </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div>
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