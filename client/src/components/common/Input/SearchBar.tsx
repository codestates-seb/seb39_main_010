import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { AiOutlineSearch } from 'react-icons/ai';
import { useDebounce } from 'hooks/useDebounce';
import { FilterState } from 'components/pages/Study/FilterAndSearchBar';

export const SearchContainer = styled.div`
	display: flex;
	align-items: center;
	padding: 9px 20px;
	gap: 10px;

	width: 277px;
	height: 46px;

	background: #f9f9fc;
	border: 1px solid #cfcfcf;
	border-radius: 22px;
	form {
		display: flex;
		align-items: center;
		div {
			margin-right: 5px;
		}
		input {
			width: 200px;

			background: none;
			border: none;
			outline: none;
			color: #000000;
			font-size: 15px;
			line-height: 40px;
		}
	}
`;

interface SearchBarProps {
	placeholder: string;
	filterState: FilterState;
	setFilterState: React.Dispatch<React.SetStateAction<FilterState>>;
}

const SearchBar = ({
	placeholder,
	filterState,
	setFilterState,
}: SearchBarProps) => {
	const [keyword, setKeyword] = useState('');
	const debouncedKeyword = useDebounce(keyword, 500);

	const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
		setKeyword(event.target.value);
	};

	useEffect(() => {
		setFilterState({ ...filterState, ['keyword']: debouncedKeyword });
	}, [debouncedKeyword]);

	return (
		<SearchContainer>
			<form>
				<div>
					<AiOutlineSearch size={25} />
				</div>
				<input
					type="text"
					placeholder={placeholder}
					onChange={handleChange}
					value={keyword}
				/>
			</form>
		</SearchContainer>
	);
};

export default SearchBar;
