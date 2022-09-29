import styled from 'styled-components';

export const BoxCategoryContainer = styled.div`
	width: 1000px;
	display: flex;
	border-bottom: 1px solid #dbdbdb;
	div {
		padding: 0 20px 17px 20px;

		font-size: 20px;
		font-weight: 500;
		color: #b1b1b1;
	}
	.submenu {
		cursor: pointer;
	}
	.focused {
		border-bottom: 3px solid #000000;
		color: black;
	}
`;

export const SortingBoxContainer = styled.div`
	width: 1000px;

	div:first-child {
		display: flex;
	}
`;
