import React from 'react';
import styled from 'styled-components';

export const CategoryContainer = styled.div`
	width: 1200px;
	height: 52px;
	box-shadow: inset 0px -1px 1px rgba(0, 0, 0, 0.1);

	display: flex;
	flex-direction: row;
	align-items: center;
	padding: 20px;

	background-color: lightcyan;

	ul {
		display: flex;
		flex-direction: row;
		align-items: center;
		padding: 0px;
		gap: 30px;

		/* Inside auto layout */

		flex: none;
		order: 1;
		flex-grow: 0;
		li {
			&:hover {
				height: 52px;
				padding-top: 18px;
				font-weight: bold;
				cursor: pointer;
				border-bottom: solid 2px #000000;
			}
		}
	}
`;

const Category = () => {
	return (
		<CategoryContainer>
			<ul>
				<li>전체</li>
				<li>모집중</li>
				<li>모집완료</li>
			</ul>
		</CategoryContainer>
	);
};
export default Category;
