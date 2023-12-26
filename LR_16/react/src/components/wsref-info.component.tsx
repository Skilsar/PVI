import {useState} from "react";
import {WsrefFormComponent} from "./wsref-form.component";
import {WsrefCommentListComponent} from "./wsref-comment-list.component";
import {RoleNames} from "../util/role-names";

export function WsrefInfoComponent({role, wsref, updateWsref, deleteWsref, incrementWsref, decrementWsref}: {
    role: RoleNames,
    wsref: any,
    updateWsref: (id: number, wsrefDto: any) => Promise<any>,
    deleteWsref: (id: number) => Promise<any>,
    incrementWsref: (id: number) => Promise<any>,
    decrementWsref: (id: number) => Promise<any>
}) {
    let [updating, setUpdating] = useState(false);
    let [deleting, setDeleting] = useState(false);
    let [comments, setComments] = useState(false);
    return (
        <fieldset>
            {role === RoleNames.ADMIN ? <>
                <button onClick={(e: any) => setDeleting(true)}>delete</button>
                <button onClick={(e: any) => setUpdating(true)}>update</button>
            </> : null}
            <button onClick={(e: any) => incrementWsref(wsref.id)}>+{wsref.plus}</button>
            <button onClick={(e: any) => decrementWsref(wsref.id)}>-{wsref.minus}</button>
            <button onClick={(e: any) => setComments(!comments)}>comments</button>
            [{wsref.id}]
            <a href={wsref.url}>{wsref.description.toUpperCase()}</a>
            {updating ? <WsrefFormComponent wsref={wsref}
                                            updateWsref={async (wsrefDto: any) => {
                                                await updateWsref(wsref.id, wsrefDto);
                                                setUpdating(false);
                                            }} onClose={() => setUpdating(false)}/> : null}
            {deleting ? <div>
                {wsref.id}
                {wsref.description.toUpperCase()}
                <div>
                    <button onClick={async (e: any) => {
                        await deleteWsref(wsref.id);
                        setDeleting(false);
                    }}>OK
                    </button>
                    <button onClick={(e: any) => setDeleting(false)}>Cancel</button>
                </div>
            </div> : null}
            {comments ? <WsrefCommentListComponent role={role} wsref={wsref}/> : null}
        </fieldset>
    );
}