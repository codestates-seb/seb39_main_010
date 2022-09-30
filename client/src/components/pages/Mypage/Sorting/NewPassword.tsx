import { BasicButton, Input } from 'components/common';
import React from 'react';
import styled from 'styled-components';

export const NewPasswordsContainer = styled.div`
	width: 1000px;

	button {
		width: 270px;
	}
	span {
		font-size: 18px;
	}
	input {
		width: 600px;
		margin: 12px 0 35px 0;
	}
`;

const NewPasswords = () => {
	return (
		<NewPasswordsContainer>
			<div>
				<span>이전 비밀번호</span>
				<Input placeholder="영문 소문자, 숫자 조합 6자 이상의 비밀번호" />
			</div>
			<div>
				<span>새 비밀번호</span>
				<Input placeholder="영문 소문자, 숫자 조합 6자 이상의 비밀번호" />
			</div>
			<div>
				<span>새 비밀번호 확인</span>
				<Input placeholder="영문 소문자, 숫자 조합 6자 이상의 비밀번호" />
			</div>
			<BasicButton mode="login">비밀번호 변경</BasicButton>
		</NewPasswordsContainer>
	);
};

export default NewPasswords;
