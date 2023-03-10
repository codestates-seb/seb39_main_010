import React from 'react';
import ErrorLayout from 'components/common/ErrorLayout/ErrorLayout';

const NetworkError = () => {
	return (
		<ErrorLayout>
			<div>현재 서버 에러로 서비스 이용이 불가합니다.</div>
			<div>서비스 이용에 불편을 드려 죄송합니다.</div>
		</ErrorLayout>
	);
};

export default NetworkError;
