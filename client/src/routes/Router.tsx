import React from 'react';
import { Route, Routes } from 'react-router-dom';
import { Main } from 'pages/Main';
import { Signup } from 'pages/Signup';
import { Study, StudyDetail } from 'pages/Study';

const Router = () => {
	return (
		<React.Fragment>
			<Routes>
				<Route path="/" element={<Main />} />
				<Route path="/signup" element={<Signup />} />
				<Route path="/study-board">
					<Route path="" element={<Study />} />
					<Route path=":id" element={<StudyDetail />} />
				</Route>
			</Routes>
		</React.Fragment>
	);
};

export default Router;