import { BasicButton, Intro } from 'components/common';
import { Contents } from 'components/pages';
import React from 'react';
import styled from 'styled-components';

const StudyWriteContainer = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
`;

const StudyWrite = () => {
	return (
		<StudyWriteContainer>
			<Intro
				title={`스터디 모집글 작성하기`}
				content={`함께 성장하고 싶은 스터디를 직접 모집해 보세요! `}
			/>
			<Contents />
			<BasicButton width="500px" height="66px" mode="basic">
				등록하기
			</BasicButton>
		</StudyWriteContainer>
	);
};

export default StudyWrite;
