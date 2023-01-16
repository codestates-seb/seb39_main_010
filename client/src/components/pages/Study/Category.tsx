import React, { useState } from 'react';
import styled from 'styled-components';
import { CardContentAll, CardContentDone, CardContentOn } from './Cards';
// import SearchBar from './FilterAndSearchBar';

export const CategoryContainer = styled.div`
	width: 1200px;
	height: 50px;
	border-bottom: 1px solid #dbdbdb;

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
			{/* <SearchBar
				placeholder="관심 스터디를 검색해 보세요!"
				url="/study/write"
			/> */}
			<div>{currentpage}</div>
		</>
	);
};
export default Category;
