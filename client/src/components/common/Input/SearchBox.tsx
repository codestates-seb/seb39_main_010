import React from 'react';
import styled from 'styled-components';
import { AiOutlineSearch } from 'react-icons/ai';
import { isPropertySignature } from 'typescript';

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

const Search: React.FC<{ text: string }> = (props) => {
	return (
		<SearchContainer>
			<form>
				<div>
					<AiOutlineSearch size={25} />
				</div>
				<input type="text" placeholder={props.text} />
			</form>
		</SearchContainer>
	);
};
export default Search;
