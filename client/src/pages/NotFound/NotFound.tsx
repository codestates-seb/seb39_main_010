import React from 'react';
import ErrorLayout from 'components/common/ErrorLayout/ErrorLayout';

const NotFound = () => {
	return (
		<ErrorLayout>
			<div>죄송합니다. 해당 페이지를 찾을 수 없습니다.</div>
			<div>
				<span>404</span> NOT FOUND
			</div>
		</ErrorLayout>
	);
};

export default NotFound;
