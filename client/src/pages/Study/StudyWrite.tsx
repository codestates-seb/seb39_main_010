import { Intro } from 'components/common';
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
		</StudyWriteContainer>
	);
};

export default StudyWrite;
