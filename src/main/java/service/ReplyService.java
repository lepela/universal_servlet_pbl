package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.Reply;
import mapper.ReplyMapper;
import util.MybatisUtil;

public class ReplyService {
	public Reply findBy(Long rno) {
		try (SqlSession session = MybatisUtil.getSqlSession()) {
			ReplyMapper mapper = session.getMapper(ReplyMapper.class);
			return mapper.selectOne(rno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<Reply> list(Long bno, Long lastRno) {
		try (SqlSession session = MybatisUtil.getSqlSession()) {
			ReplyMapper mapper = session.getMapper(ReplyMapper.class);
			return mapper.list(bno, lastRno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void register(Reply Reply) {
		try (SqlSession session = MybatisUtil.getSqlSession()) {
			ReplyMapper mapper = session.getMapper(ReplyMapper.class);
			mapper.insert(Reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void modify(Reply Reply) {
		try (SqlSession session = MybatisUtil.getSqlSession()) {
			ReplyMapper mapper = session.getMapper(ReplyMapper.class);
			mapper.update(Reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void remove(Long rno) {
		try (SqlSession session = MybatisUtil.getSqlSession()) {
			ReplyMapper mapper = session.getMapper(ReplyMapper.class);
			 mapper.delete(rno);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
