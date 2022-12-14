import { Intro } from 'components/common';
import { ContentsR } from 'components/pages';
import React from 'react';
import styled from 'styled-components';

const ReviewWriteContainer = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
`;

const ReviewWrite = () => {
	return (
		<ReviewWriteContainer>
			<Intro
				title={`면접 후기 작성하기`}
				content={`소중한 면접 경험을 함께 공유해 주세요`}
			/>
			<ContentsR />
		</ReviewWriteContainer>
	);
};

export default ReviewWrite;
