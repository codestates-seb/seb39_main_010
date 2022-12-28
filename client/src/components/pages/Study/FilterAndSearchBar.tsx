import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { SearchBar, SortByFilter } from 'components/common';
import { BsPencil } from 'react-icons/bs';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { Question } from '../Question/QuestionCard';
import JobFilter from 'components/common/SelectBox/JobFilter';
import { getFilteredQuestionList } from 'apis/apiClient';

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
	url: string;
	setDataList: React.Dispatch<React.SetStateAction<Question[] | undefined>>;
}

export interface FilterState {
	searchType: 'title';
	keyword: string;
	orderby: string;
	category: string;
}

export const FilterAndSearchBar = ({
	placeholder,
	url,
	setDataList,
}: IProps) => {
	const navigate = useNavigate();
	const [filterState, setFilterState] = useState<FilterState>({
		searchType: 'title',
		keyword: '',
		orderby: '',
		category: '',
	});
	const [, setSearchParams] = useSearchParams();

	useEffect(() => {
		getFilteredQuestionList(
			filterState.keyword,
			filterState.orderby,
			filterState.category
		).then((res) => {
			setDataList(res);
			if (filterState.orderby && filterState.category) {
				setSearchParams({
					searchType: 'title',
					keyword: filterState.keyword,
					orderby: filterState.orderby,
					category: filterState.category,
				});
			} else if (!filterState.orderby && filterState.category) {
				setSearchParams({
					searchType: 'title',
					keyword: filterState.keyword,
					category: filterState.category,
				});
			} else if (filterState.orderby && !filterState.category) {
				setSearchParams({
					searchType: 'title',
					keyword: filterState.keyword,
					orderby: filterState.orderby,
				});
			} else {
				setSearchParams({
					searchType: 'title',
					keyword: filterState.keyword,
				});
			}
		});
	}, [filterState]);

	return (
		<SearchBarContainer>
			<div>
				<SearchBar
					filterState={filterState}
					setFilterState={setFilterState}
					placeholder={placeholder}
				/>
				<JobFilter
					setDataList={setDataList}
					filterState={filterState}
					setFilterState={setFilterState}
				/>
				<SortByFilter
					setDataList={setDataList}
					filterState={filterState}
					setFilterState={setFilterState}
				/>
			</div>

			<WriteButton onClick={() => navigate(url)}>
				<BsPencil size={20} />
				작성하기
			</WriteButton>
		</SearchBarContainer>
	);
};

export default FilterAndSearchBar;
