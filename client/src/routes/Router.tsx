import React from 'react';
import { Navigate, Route, Routes } from 'react-router-dom';
import { Signup } from 'pages/Signup';
import { Study, StudyDetail, StudyWrite } from 'pages/Study';
import {
	Question,
	QuestionDetail,
	QuestionWrite,
	Review,
	ReviewWrite,
} from 'pages/Interveiw';
import { Mypage } from 'pages/Mypage';
import { Footer, Header } from 'components/common';
import QuestionEdit from 'pages/Interveiw/Question/QuestionEdit';

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
					<Route path="question/:id" element={<QuestionDetail />} />
					<Route path="question/write" element={<QuestionWrite />} />
					<Route path="question/edit/:id" element={<QuestionEdit />} />
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
			<Footer />
		</React.Fragment>
	);
};

export default Router;
