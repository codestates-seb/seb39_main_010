import React from 'react';
import styled from 'styled-components';
import { AiOutlineSearch } from 'react-icons/ai';

export const SearchContainer = styled.div`
	display: flex;
	flex-direction: row;
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
			float: left;
			padding: 0;
			background: none;
			border: none;
			outline: none;
			color: #000000;
			font-size: 15px;
			line-height: 40px;
		}
	}
`;

const Search = () => {
	return (
		<SearchContainer>
			<form>
				<div>
					<AiOutlineSearch size={25} />
				</div>
				<input type="text" placeholder="관심스터디를 검색해 보세요!" />
			</form>
		</SearchContainer>
	);
};
export default Search;
