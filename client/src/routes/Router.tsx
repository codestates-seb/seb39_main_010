import React from 'react';
import { Navigate, Route, Routes } from 'react-router-dom';
import { Signup } from 'pages/Signup';
import { Study, StudyDetail } from 'pages/Study';
import { Question, QuestionDetail, Review } from 'pages/Interveiw';

const Router = () => {
	return (
		<React.Fragment>
			<Routes>
				<Route path="/" element={<Navigate to="/interview/question" />} />
				<Route path="/signup" element={<Signup />} />
				<Route path="/mypage" element={<div>Mypage</div>} />
				<Route path="/profile/:id" element={<div>Profile</div>} />
				<Route path="/interview">
					<Route path="question" element={<div>Question</div>} />
					<Route path="question/:id" element={<div>QuestionDetail</div>} />
					<Route path="review" element={<Review />} />
					<Route path="review/:id" element={<div>ReviewDetail</div>} />
				</Route>
				<Route path="/study">
					<Route path="" element={<Study />} />
					<Route path=":id" element={<StudyDetail />} />
				</Route>
			</Routes>
		</React.Fragment>
	);
};

export default Router;
