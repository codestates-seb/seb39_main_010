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
import PrivateRoute from './PrivateRoute';
import NotFound from 'pages/NotFound/NotFound';

const Router = () => {
	return (
		<React.Fragment>
			<Header />
			<Routes>
				<Route path="/*" element={<NotFound />} />
				<Route path="/" element={<Navigate to="/interview/question" />} />
				<Route path="/interview">
					<Route path="question" element={<Question />} />
					<Route path="question/:id" element={<QuestionDetail />} />
					<Route path="review" element={<Review />} />
					<Route path="review/:id" element={<div>ReviewDetail</div>} />
				</Route>
				<Route path="/study">
					<Route path="" element={<Study />} />
					<Route path=":id" element={<StudyDetail />} />
				</Route>
				<Route element={<PrivateRoute authentication={true} />}>
					<Route path="/mypage" element={<Mypage />} />
					<Route path="/profile/:id" element={<Mypage />} />
					<Route path="/interview/question/write" element={<QuestionWrite />} />
					<Route
						path="/interview/question/edit/:id"
						element={<QuestionEdit />}
					/>
					<Route path="/interview/review/write" element={<ReviewWrite />} />
					<Route path="/study/write" element={<StudyWrite />} />
				</Route>
				<Route element={<PrivateRoute authentication={false} />}>
					<Route path="/signup" element={<Signup />} />
				</Route>
			</Routes>
			<Footer />
		</React.Fragment>
	);
};

export default Router;
