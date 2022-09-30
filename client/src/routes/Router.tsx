import React from 'react';
import { Navigate, Route, Routes } from 'react-router-dom';
import { Signup } from 'pages/Signup';
import { Study, StudyDetail, StudyWrite } from 'pages/Study';
import { Question, Review, ReviewWrite } from 'pages/Interveiw';
import { Mypage } from 'pages/Mypage';
import { Header } from 'components/common';

const Router = () => {
	return (
		<React.Fragment>
			<Header />
			<Routes>
				<Route path="/" element={<Navigate to="/interview/question" />} />
				<Route path="/signup" element={<Signup />} />
				<Route path="/mypage" element={<Mypage />} />
				<Route path="/profile/:id" element={<div>Profile</div>} />
				<Route path="/interview">
					<Route path="question" element={<Question />} />
					<Route path="question/:id" element={<div>QuestionDetail</div>} />
					<Route path="review" element={<Review />} />
					<Route path="review/:id" element={<div>ReviewDetail</div>} />
					<Route path="review/write" element={<ReviewWrite />} />
				</Route>
				<Route path="/study">
					<Route path="" element={<Study />} />
					<Route path=":id" element={<StudyDetail />} />
					<Route path="write" element={<StudyWrite />} />
				</Route>
			</Routes>
		</React.Fragment>
	);
};

export default Router;
