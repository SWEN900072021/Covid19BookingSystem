# Concurrency Examples #


## Book Timeslot Concurrency ##
1. Open two or more tabs (incognito) and log in with multiple users.
2. Navigate to the book timeslot page, and choose the same vaccine type for all the users.
3. Fill out the questionnaire and proceed to the search page.
4. Enter the same search query for all the users.
5. On the calendar view, proceed to select the same timeslot for all the users, and navigate to the confirmation page.
Click ‘Book Timeslot’ on all the timeslots at the same time. 

The expected result is only one of the multiple users should be redirected to a success page. The rest of the users should be redirected to the search timeslot page with an error message describing that another user has already booked the timeslot.


![book_timeslot_concurrency](resources/book_timeslot_concurrency.gif)