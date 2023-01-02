import React, { ReactElement } from 'react';
import { Outlet } from 'react-router-dom';
import { getUser } from 'utils/user';
import { Question } from 'pages/Interveiw';
import LoginRequestModal from 'components/common/LoginModal/LoginRequestModal';

interface PrivateRouteProps {
	children?: ReactElement;
	authentication: boolean;
}

const PrivateRoute = ({
	authentication,
}: PrivateRouteProps): React.ReactElement => {
	const isLoggedin = getUser();
	if (authentication) {
		return isLoggedin ? <Outlet /> : <LoginRequestModal />;
	} else {
		return isLoggedin ? <Question /> : <Outlet />;
	}
};

export default PrivateRoute;
