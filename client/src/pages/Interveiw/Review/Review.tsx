import React from 'react';
import styled from 'styled-components';
import { Intro } from 'components/common';
import { Reviews } from 'components/pages';

export const ReviewContainer = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
`;

export const Filtering = styled.div`
	margin-right: 20px;

	div {
		height: 46px;
		line-height: 46px;
		padding: 0 10px;

		font-weight: bold;
		color: #b1b1b1;

		&:hover {
			border-bottom: 3px solid #000000;
			cursor: pointer;
			color: black;
		}
	}
`;

const Review = () => {
	return (
		<ReviewContainer>
			<Intro
				title={`면접후기`}
				content={`소중한 면접 경험을 같이 공유해 주세요.`}
			/>

			{/* <FilterAndSearchBar
				placeholder="궁금한 기업을 찾아보세요"
				url="/interview/review/write"
			/> */}
			<Reviews />
		</ReviewContainer>
	);
};

export default Review;
