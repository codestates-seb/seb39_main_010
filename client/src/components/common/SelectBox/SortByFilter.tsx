import { Question } from 'components/pages/Question/QuestionCard';
import { FilterState } from 'components/pages/Study/FilterAndSearchBar';
import React from 'react';
import styled from 'styled-components';

export const SortingContainer = styled.div`
	display: flex;
	flex-direction: row;
	align-items: center;
	padding: 9px 16px;
	margin-left: 15px;
	gap: 6px;

	height: 46px;

	background: #f9f9fc;
	border: 1px solid #cfcfcf;
	border-radius: 22px;

	select {
		float: right;
		padding: 0;
		background: none;
		border: none;
		outline: none;
		color: #000000;
		font-size: 15px;
		line-height: 40px;
	}
`;

export interface FilterProps {
	setDataList: React.Dispatch<React.SetStateAction<Question[]>>;
	filterState: FilterState;
	setFilterState: React.Dispatch<React.SetStateAction<FilterState>>;
}

const SortByFilter = ({ filterState, setFilterState }: FilterProps) => {
	const onOptionChange = (event: React.FormEvent<HTMLSelectElement>) => {
		setFilterState({ ...filterState, ['orderby']: event.currentTarget.value });
	};

	return (
		<SortingContainer>
			<form>
				<select onChange={onOptionChange}>
					<option value="">최신순</option>
					<option value="likeCount">좋아요순</option>
				</select>
			</form>
		</SortingContainer>
	);
};
export default SortByFilter;
