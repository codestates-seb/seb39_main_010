import React from 'react';
import { BsFillPersonFill, BsChatLeftDots, BsSuitHeart } from 'react-icons/bs';
import { Bottom, CardContainer, Middle, Top } from './style';

const Card = () => {
	return (
		<CardContainer>
			<div className="box">
				<Top>
					<div>모집중</div>
					<div>2022.09.16</div>
				</Top>
				<Middle>
					<div>
						<h3>
							Keepit Alpha 사이드 프로젝트 같이 성장 하실 리액트 네이티브 개발자
							구
						</h3>
						<div>#프론트엔드 #파이썬 #코딩 #프로젝트 #스터디</div>
					</div>
					<p>
						여행을 다니거나 주말마다 갈곳이 없는지 찾는 경우가 많았습니다. 이때
						좋은 맛집이나 재밌을 것 같은 플레이스를 인스타나 ...
					</p>
				</Middle>
				<Bottom>
					<div>
						<div>
							<BsFillPersonFill size={20} />
						</div>
						<span>닉네임</span>
					</div>
					<div>
						<div className="likes">
							<div className="hearticon">
								<BsSuitHeart size={20} />
							</div>
							<span>0</span>
						</div>
						<div className="comments">
							<div className="chaticon">
								<BsChatLeftDots size={20} />
							</div>
							<span>3</span>
						</div>
					</div>
				</Bottom>
			</div>
		</CardContainer>
	);
};
export default Card;
