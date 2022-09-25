import React from 'react';
import styled from 'styled-components';
import { SearchBox, SortingRound } from 'components/common';
import { BsPencil } from 'react-icons/bs';
import { useNavigate } from 'react-router-dom';

export const SearchBarContainer = styled.div`
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
const SearchBar = () => {
	const navigate = useNavigate();
	const OPTIONS = [
		{ value: 'new', name: '최신순' },
		{ value: 'popular', name: '인기순' },
	];
	return (
		<SearchBarContainer>
			<div>
				<SearchBox text={`관심 스터디를 검색해 보세요!`} />
				<SortingRound options={OPTIONS} />
			</div>

			<WriteButton onClick={() => navigate('/study/write')}>
				<BsPencil size={20} />
				작성하기
			</WriteButton>
		</SearchBarContainer>
	);
};
export default SearchBar;
