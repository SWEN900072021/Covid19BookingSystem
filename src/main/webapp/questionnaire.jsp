<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Questionnaire</title>
</head>
<body>
<form action = "questionnaire" method = "post">
    Question 1: Do you have allergies to medications, food, a vaccine component, or latex?
    <select name="allergy_medication">
        <option value="no">No</option>
        <option value="yes">Yes</option>
        ...
    </select><br/>
    Question 2: Have you ever had a serious reaction after receiving a vaccination?
    <select name="vaccine_reaction">
        <option value="no">No</option>
        <option value="yes">Yes</option>
        ...
    </select><br/>
    Question 3: Do you have a long-term health problem with heart, lung, kidney, or metabolic disease
    (e.g., diabetes), asthma, a blood disorder, no spleen, complement component deficiency,
    a cochlear implant, or a spinal fluid leak? Are you on long-term aspirin therapy?
    <select name="long_term_health_problems">
        <option value="no">No</option>
        <option value="yes">Yes</option>
        ...
    </select><br/>
    Question 4: Do you have cancer, leukemia, HIV/AIDS, or any other immune system problem?
    <select name="immune_problems">
        <option value="no">No</option>
        <option value="yes">Yes</option>
        ...
    </select><br/>
    Question 5: Do you have a parent, brother, or sister with an immune system problem?
    <select name="family_immune_problems">
        <option value="no">No</option>
        <option value="yes">Yes</option>
        ...
    </select><br/>
    Question 6: In the past 3 months, have you taken medications that affect your immune system, such
    as prednisone, other steroids, or anticancer drugs; drugs for the treatment of rheumatoid
    arthritis, Crohn’s disease, or psoriasis; or have you had radiation treatments?
    <select name="immune_medication">
        <option value="no">No</option>
        <option value="yes">Yes</option>
        ...
    </select><br/>
    Question 7: Have you had a seizure or a brain or other nervous system problem?
    <select name="nervous_problem">
        <option value="no">No</option>
        <option value="yes">Yes</option>
        ...
    </select><br/>
    Question 8: During the past year, have you received a transfusion of blood or blood products,
    or been given immune (gamma) globulin or an antiviral drug?
    <select name="blood_problem">
        <option value="no">No</option>
        <option value="yes">Yes</option>
        ...
    </select><br/>
    <input type = "submit" value = "Submit Questionaire">
</form>
</body>
</html>
