import React from 'react';
import styled from 'styled-components';
import { PopularSorting, SearchBar } from 'components/common';
import { BsPencil } from 'react-icons/bs';
import { useNavigate } from 'react-router-dom';
import JobSorting from 'components/common/SelectBox/JobSorting';

export const SearchBarContainer = styled.div`
	display: flex;
	justify-content: space-between;
	margin-top: 1.5rem;

	width: 1200px;

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

interface IProps {
	placeholder: string;
	navigate: string;
}

export const FilterAndSearchBar = (props: IProps) => {
	const navigate = useNavigate();
	return (
		<SearchBarContainer>
			<div>
				<SearchBar placeholder={props.placeholder} />
				<JobSorting />
				<PopularSorting />
			</div>

			<WriteButton onClick={() => navigate(props.navigate)}>
				<BsPencil size={20} />
				작성하기
			</WriteButton>
		</SearchBarContainer>
	);
};

export default FilterAndSearchBar;
