import React from 'react';
import { JobOption, jobOptions } from './JobSelect';
import { FilterProps, SortingContainer } from './SortByFilter';

const JobFilter = ({ filterState, setFilterState }: FilterProps) => {
	const onOptionChange = (event: React.FormEvent<HTMLSelectElement>) => {
		setFilterState({ ...filterState, ['category']: event.currentTarget.value });
	};

	return (
		<SortingContainer>
			<form>
				<select onChange={onOptionChange}>
					<option value="">직무전체</option>
					{jobOptions.map((option: JobOption) => (
						<option key={option.value} value={option.name}>
							{option.name}
						</option>
					))}
				</select>
			</form>
		</SortingContainer>
	);
};
export default JobFilter;
