
public class Test1 {
	from .constants import BOT_WELCOME_MESSAGE, PYTHON_QUESTION_LIST


	def generate_bot_responses(message, session):
	    bot_responses = []

	    current_question_id = session.get("current_question_id")
	    if not current_question_id:
	        bot_responses.append(BOT_WELCOME_MESSAGE)

	    success, error = record_current_answer(message, current_question_id)

	    if not success:
	        return [error]

	    next_question, next_question_id = get_next_question(current_question_id)

	    if next_question:
	        bot_responses.append(next_question)
	    else:
	        final_response = generate_final_response(session)
	        bot_responses.append(final_response)

	    session["current_question_id"] = next_question_id
	    session.save()

	    return bot_responses


	def record_current_answer(answer, current_question_id):
	    '''
	    Validates and stores the answer for the current question to session.
	    '''
	current question =
	PYTHON QUESTION LIST current question id]

	if not isinstance (answer, str):
	return False,
	"Invalid answer
	format. Please provide a string."
	session["user_answers"]
	[current_question id] = answer
	 return True, ""


	def get_next_question(current_question_id):
	    '''
	    Fetches the next question from the PYTHON_QUESTION_LIST based on the current_question_id.
	    '''
	  try:
	        current_index = PYTHON_QUESTION_LIST.index(current_question_id)
	    except ValueError:
	        return None, -1

	    if current_index == len(PYTHON_QUESTION_LIST) - 1:
	        return None, -1

	    next_question_id = current_index + 1
	    next_question = PYTHON_QUESTION_LIST[next_question_id]

	    return next_question, next_question_id
	    return "dummy question", -1


	   def generate_final_response(session):
	    '''
	    Creates a final result message including a score based on the answers
	    by the user for questions in the PYTHON_QUESTION_LIST.
	    '''
	       by the user for questions in the PYTHON_QUESTION_LIST.
	    '''

	    user_answers = session.get("user_answers", {})

	    total_questions = len(PYTHON_QUESTION_LIST)
	    correct_answers = 0

	    for question_id, correct_answer in enumerate(PYTHON_QUESTION_LIST):
	        user_answer = user_answers.get(question_id)

	        if user_answer is not None and user_answer == correct_answer["correct_option"]:
	            correct_answers += 1

	    score_percentage = (correct_answers / total_questions) * 100

	    final_response = f"Your quiz is complete!\n"
	    final_response += f"You answered {correct_answers} out of {total_questions} questions correctly.\n"
	    final_response += f"Your score: {score_percentage:.2f}%"

	    return final_response
	    return "dummy result"
}
