import {useState} from "react";
import {WsrefCommentFormComponent} from "./wsref-comment-form.component";
import {RoleNames} from "../util/role-names";

export function WsrefCommentInfoComponent({role, wsrefComment, updateWsrefComment, deleteWsrefComment}: {
    role: RoleNames,
    wsrefComment: any,
    updateWsrefComment: (id: number, wsrefCommentDto: any) => Promise<any>,
    deleteWsrefComment: (id: number) => Promise<any>
}) {
    let [updating, setUpdating] = useState(false);
    let [deleting, setDeleting] = useState(false);
    let stamp = new Date(wsrefComment.stamp);
    return (
        <fieldset>
            <legend>{stamp.getFullYear()}-{stamp.getMonth() + 1}-{stamp.getDate()} {stamp.getHours()}:{stamp.getMinutes()}:{stamp.getSeconds()}</legend>
            {!updating && !deleting ? <> {role === RoleNames.ADMIN || sessionStorage.getItem("sessionId") === wsrefComment.sessionId ?
                <div>
                    <button onClick={(e: any) => setDeleting(true)}>delete</button>
                    <button onClick={(e: any) => setUpdating(true)}>update</button>
                </div> : null}
                <div className={"flex"}>
                    <textarea readOnly={true}>{wsrefComment.comtext}</textarea>
                </div>
            </> : null}
            {updating ? <WsrefCommentFormComponent wsrefComment={wsrefComment}
                                                   updateWsrefComment={async (wsrefCommentDto: any) => {
                                                       await updateWsrefComment(wsrefComment.id, wsrefCommentDto);
                                                       setUpdating(false);
                                                   }} onClose={() => setUpdating(false)}/> : null}
            {deleting ? <div>
                delete
                <div className={"flex"}>
                    <textarea readOnly={true}>{wsrefComment.comtext}</textarea>
                </div>
                <div>
                    <button onClick={async (e: any) => {
                        await deleteWsrefComment(wsrefComment.id);
                        setDeleting(false);
                    }}>OK
                    </button>
                    <button onClick={(e: any) => setDeleting(false)}>Cancel</button>
                </div>
            </div> : null}
        </fieldset>
    );
}