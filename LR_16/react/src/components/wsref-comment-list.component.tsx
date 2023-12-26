import {useEffect, useState} from "react";
import {WsrefCommentFormComponent} from "./wsref-comment-form.component";
import {WsrefCommentInfoComponent} from "./wsref-comment-info.component";
import {ApiManager} from "../util/api.manager";
import {RoleNames} from "../util/role-names";

export function WsrefCommentListComponent({role, wsref}: {
    role: RoleNames,
    wsref: any
}) {
    let [wsrefCommentList, setWsrefCommentList] = useState<any[]>();
    let [inserting, setInserting] = useState(false);
    const loadWsrefComments = async () => {
        let wsrefCommentsData = await ApiManager.get(`/wsrefcomment`, {
            wsref: wsref.id
        });
        if (wsrefCommentsData)
            setWsrefCommentList(wsrefCommentsData);
    }
    const insertWsrefComment = async (wsrefCommentDto: any) => {
        await ApiManager.post(`/wsrefcomment`, wsrefCommentDto);
        loadWsrefComments();
    }
    const updateWsrefComment = async (id: number, wsrefCommentDto: any) => {
        await ApiManager.put(`/wsrefcomment/${id}`, wsrefCommentDto);
        loadWsrefComments();
    }
    const deleteWsrefComment = async (id: number) => {
        await ApiManager.delete(`/wsrefcomment/${id}`);
        loadWsrefComments();
    }
    useEffect(() => {
        loadWsrefComments();
    }, []);
    return (
        <fieldset>
            <h2>-- UWSR Comments/{wsref.id} --</h2>
            <div>
                <button onClick={(e: any) => setInserting(true)}>insert</button>
            </div>
            {inserting ? <fieldset>
                <WsrefCommentFormComponent wsrefId={wsref.id}
                                           insertWsrefComment={async (wsrefCommentDto: any) => {
                                               await insertWsrefComment(wsrefCommentDto);
                                               setInserting(false);
                                           }} onClose={() => setInserting(false)}/>
            </fieldset> : null}
            {wsrefCommentList?.map((wsrefComment: any) => <WsrefCommentInfoComponent key={wsrefComment.id}
                                                                                     role={role}
                                                                                     wsrefComment={wsrefComment}
                                                                                     updateWsrefComment={updateWsrefComment}
                                                                                     deleteWsrefComment={deleteWsrefComment}/>)}
        </fieldset>
    );
}