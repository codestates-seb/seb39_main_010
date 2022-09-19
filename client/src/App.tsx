import Header from 'components/common/Header/Header';
import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Router from 'routes/Router';

function App() {
	return (
		<BrowserRouter>
			<Header />
			<Routes>
				<Route path="*" element={<Router />} />
			</Routes>
		</BrowserRouter>
	);
}

export default App;
