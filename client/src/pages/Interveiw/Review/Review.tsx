import React from 'react';
import styled from 'styled-components';
import { Intro, SearchBox, SortingRound } from 'components/common';
import { Reviews } from 'components/pages';

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

// export Job

const Review = () => {
	const OPTIONS = [
		{ value: 'all', name: '직무전체' },
		{ value: 'sales', name: '영업/고객상담' },
		{ value: 'business', name: '경영/사무' },
		{ value: 'marketing', name: '마케팅/광고/홍보' },
		{ value: 'produce', name: '생산/제조' },
		{ value: 'research', name: '연구개발/설계' },
		{ value: 'internet', name: 'IT/인터넷' },
		{ value: 'service', name: '서비스' },
		{ value: 'trade', name: '무역/유통' },
		{ value: 'medical', name: '의료' },
		{ value: 'structure', name: '건설' },
		{ value: 'education', name: '교육' },
		{ value: 'design', name: '디자인' },
		{ value: 'profession', name: '전문/특수직' },
		{ value: 'media', name: '미디어' },
	];
	return (
		<ReviewContainer>
			<Intro
				title={`면접후기`}
				content={`소중한 면접 경험을 같이 공유해 주세요.`}
			/>

			<SearchBar>
				<div>
					<SearchBox text={`궁금한 기업을 찾아보세요.`} />
					<SortingRound options={OPTIONS} />
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
