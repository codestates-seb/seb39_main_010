import Intro from 'components/common/Intro/Intro';
import SearchBox from 'components/common/SearchBox/SearchBox';
import Sorting from 'components/common/Sorting/Sorting';
import Category from 'components/pages/Study/Category';
import React from 'react';
import styled from 'styled-components';
import { BsPencil } from 'react-icons/bs';

export const StudyContainer = styled.div`
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

export const WriteButton = styled.button`
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: center;
	padding: 9px 16px;
	gap: 3px;

	width: 129.22px;
	height: 42px;

	background: #3563e9;
	border-radius: 8px;
	border: none;

	cursor: pointer;
	color: #ffffff;
	font-size: 15px;
`;

const Study = () => {
	return (
		<StudyContainer>
			<Intro />
			<Category />
			<SearchBar>
				<div>
					<SearchBox />
					<Sorting />
				</div>

				<WriteButton>
					<BsPencil size={20} />
					작성하기
				</WriteButton>
			</SearchBar>
		</StudyContainer>
	);
};

export default Study;
