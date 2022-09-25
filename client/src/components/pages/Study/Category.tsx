import React, { useState } from 'react';
import styled from 'styled-components';
import { CardContentAll, CardContentDone, CardContentOn } from './Cards';
import SearchBar from './SearchBar';

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
	}
	.submenu {
		cursor: pointer;
	}
	.focused {
		border-bottom: 3px solid #000000;
		color: black;
	}
`;

const Category = () => {
	const [currentTab, setCurrentTab] = useState(0);
	const study = [
		{ id: 0, title: '전체', content: <CardContentAll /> },
		{ id: 1, title: '모집중', content: <CardContentOn /> },
		{ id: 2, title: '모집완료', content: <CardContentDone /> },
	];
	const selectMenuHandler = (index: number) => {
		setCurrentTab(index);
	};
	const currentpage = study[currentTab].content;

	return (
		<>
			<CategoryContainer>
				{study.map((ele, index) => (
					<div
						key={index}
						className={currentTab === index ? 'submenu focused' : 'submenu'}
						onClick={() => selectMenuHandler(index)}
					>
						<span>{ele.title}</span>
					</div>
				))}
			</CategoryContainer>
			<SearchBar />
			<div>{currentpage}</div>
		</>
	);
};
export default Category;
