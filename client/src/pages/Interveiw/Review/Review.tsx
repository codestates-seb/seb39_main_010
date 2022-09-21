import Intro from 'components/common/Intro/Intro';
import SearchBox from 'components/common/SearchBox/SearchBox';
import JobSorting from 'components/common/JobSorting/JobSorting';
import React from 'react';
import styled from 'styled-components';
import { BsPencil } from 'react-icons/bs';
import Reviews from 'components/pages/Review/Reviews';

export const ReviewContainer = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
`;

export const SearchBar = styled.div`
	display: flex;
	justify-content: space-between;
	margin-top: 1.5rem;

	width: 1200px;

	background-color: aliceblue;

	div {
		display: flex;
	}
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

			<SearchBar>
				<div>
					<SearchBox text={`궁금한 기업을 찾아보세요.`} />
					<JobSorting />
				</div>

				<Filtering>
					<div>
						<span>최신순</span>
					</div>
					<div>
						<span>인기순</span>
					</div>
				</Filtering>
			</SearchBar>
			<Reviews />
		</ReviewContainer>
	);
};

export default Review;
