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

	div {
		height: 52px;
		line-height: 52px;
		padding: 0 15px;

		font-weight: bold;
		color: #b1b1b1;

		&:hover {
			border-bottom: 3px solid #000000;
			cursor: pointer;
			color: black;
		}
	}
`;

const Category = () => {
	return (
		<CategoryContainer>
			<div>
				<span>전체</span>
			</div>
			<div>
				<span>모집중</span>
			</div>
			<div>
				<span>모집완료</span>
			</div>
		</CategoryContainer>
	);
};
export default Category;
