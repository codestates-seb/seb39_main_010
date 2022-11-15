import React from 'react';
import ReactDOM from 'react-dom/client';
import { RecoilRoot } from 'recoil';
import GlobalStyle from 'styles/GlobalStyle';
import App from './App';

const root = ReactDOM.createRoot(
	document.getElementById('root') as HTMLElement
);

root.render(
	<React.StrictMode>
		<RecoilRoot>
			<GlobalStyle />
			<App />
		</RecoilRoot>
	</React.StrictMode>
);
